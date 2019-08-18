package sales;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class SalesAppTest {

//	@Test
//	public void testGenerateReport() {
//
//		SalesApp salesApp = new SalesApp();
//		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
//
//	}

	@Test
	public void testGenerateSalesActivityReport() {
		SalesDao salesDao = mock(SalesDao.class);
		SalesReportDao mockSalesReportDao = mock(SalesReportDao.class);
		Sales spySales = spy(new Sales());
		Date today = new Date();
		ArrayList<SalesReportData> reportDataList = new ArrayList<>();
		SalesReportData mockSalesReportData = mock(SalesReportData.class);
		reportDataList.add(mockSalesReportData);
		SalesActivityReport mockSalesActivityReport = mock(SalesActivityReport.class);
		EcmService mockEcmService = mock(EcmService.class);
		SalesApp salesApp = spy(new SalesApp());

		when(salesDao.getSalesBySalesId(anyString())).thenReturn(spySales);
		doReturn(new Date(today.getTime() - 86400000L)).when(spySales).getEffectiveFrom();
		doReturn(new Date(today.getTime() + 86400000L)).when(spySales).getEffectiveTo();
		when(mockSalesReportDao.getReportData(any())).thenReturn(reportDataList);
		when(mockSalesReportData.getType()).thenReturn("SalesActivity");
		doReturn(mockSalesActivityReport).when(salesApp).generateReport(anyList(),anyList());
		when(mockSalesActivityReport.toXml()).thenReturn("XML REPORT");

		ReflectionTestUtils.setField(salesApp,SalesApp.class,"salesDao",salesDao,SalesDao.class);
		ReflectionTestUtils.setField(salesApp,SalesApp.class,"salesReportDao",mockSalesReportDao,SalesReportDao.class);
		ReflectionTestUtils.setField(salesApp,SalesApp.class,"ecmService",mockEcmService,EcmService.class);

		salesApp.generateSalesActivityReport("1",1,false,false);

		verify(mockEcmService).uploadDocument(mockSalesActivityReport.toXml());
	}

	@Test
	public void should_return_headers_contains_local_time_when_isNatTrade_is_false() {
		boolean isNatTrade = false;
		SalesApp salesApp = spy(SalesApp.class);
		List<String> result = salesApp.getHeaders(isNatTrade);
		Assert.assertTrue(result.get(3).contains("Local Time"));
	}

	@Test
	public void should_return_headers_contains__time_when_isNatTrade_is_true() {
		boolean isNatTrade = true;
		SalesApp salesApp = spy(SalesApp.class);
		List<String> result = salesApp.getHeaders(isNatTrade);
		Assert.assertTrue(result.get(3).contains("Time"));
	}

	@Test
	public void should_return_when_salesId_is_null() {
		String salesId = null;
		SalesApp salesApp = spy(SalesApp.class);
		salesApp.generateSalesActivityReport(salesId,1,false,false);
		SalesDao salesDao = mock(SalesDao.class);
		ReflectionTestUtils.setField(salesApp,SalesApp.class,"salesDao",salesDao,SalesDao.class);
		verify(salesDao,times(0)).getSalesBySalesId(salesId);
	}

	@Test
	public void should_return_when_today_is_past_sales_Effective_to_date() {
		String salesId = "1";
		SalesDao salesDao = mock(SalesDao.class);
		Sales spySales = spy(new Sales());
		SalesApp salesApp = spy(SalesApp.class);
		Date today = new Date();
		ReflectionTestUtils.setField(salesApp,SalesApp.class,"salesDao",salesDao,SalesDao.class);

		when(salesDao.getSalesBySalesId(anyString())).thenReturn(spySales);
		doReturn(new Date(today.getTime() - 96400000L)).when(spySales).getEffectiveFrom();
		doReturn(new Date(today.getTime() - 86400000L)).when(spySales).getEffectiveTo();
		salesApp.generateSalesActivityReport(salesId,1,false,false);

		verify(salesDao,times(1)).getSalesBySalesId(salesId);
		verify(salesApp, times(0)).getHeaders(false);
	}

	@Test
	public void should_return_when_today_is_before_sales_Effective_from_date() {
		String salesId = "1";
		SalesDao salesDao = mock(SalesDao.class);
		Sales spySales = spy(new Sales());
		SalesApp salesApp = spy(SalesApp.class);
		Date today = new Date();
		ReflectionTestUtils.setField(salesApp,SalesApp.class,"salesDao",salesDao,SalesDao.class);

		when(salesDao.getSalesBySalesId(anyString())).thenReturn(spySales);
		doReturn(new Date(today.getTime() + 86400000L)).when(spySales).getEffectiveFrom();
		doReturn(new Date(today.getTime() + 96400000L)).when(spySales).getEffectiveTo();
		salesApp.generateSalesActivityReport(salesId,1,false,false);

		verify(salesDao,times(1)).getSalesBySalesId(salesId);
		verify(salesApp, times(0)).getHeaders(false);
	}


}
