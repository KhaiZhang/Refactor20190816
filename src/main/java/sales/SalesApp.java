package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {
	SalesDao salesDao = new SalesDao();
	SalesReportDao salesReportDao = new SalesReportDao();
	EcmService ecmService = new EcmService();

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
		List<String> headers = null;
		if (salesId == null) {
			return;
		}
		
		Sales sales = salesDao.getSalesBySalesId(salesId);
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return;
		}
		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);
//		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
//		for (SalesReportData data : reportDataList) {
//			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
//				if (data.isConfidential()) {
//					if (isSupervisor) {
//						filteredReportDataList.add(data);
//					}
//				}else {
//					filteredReportDataList.add(data);
//				}
//			}
//		}
//
//		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
//		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
//			tempList.add(reportDataList.get(i));
//		}
//		filteredReportDataList = tempList;

		headers = getHeaders(isNatTrade);

		SalesActivityReport report = this.generateReport(headers, reportDataList);
		ecmService.uploadDocument(report.toXml());
		
	}

	protected List<String> getHeaders(boolean isNatTrade) {
		List<String> headers;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	protected SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
