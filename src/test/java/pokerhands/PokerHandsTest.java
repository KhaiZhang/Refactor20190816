package pokerhands;

import org.junit.Assert;
import org.junit.Test;

public class PokerHandsTest {

    @Test
    public void should_return_player1_win_when_palyer1_poker_value_gt_player2s(){
        String player1Poker = "1H 2H 3H 4H AS";
        String player2Poker = "1C 2C 3C 4C TS";
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.comparePoker(player1Poker, player2Poker);
        Assert.assertEquals("player1 win",result);
    }
}
