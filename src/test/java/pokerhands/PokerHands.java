package pokerhands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHands {
    public String comparePoker(String playerPoker1, String playerPoker2){
//        List<Character> poker1 = resolvePoker(playerPoker1);
//        List<Character> poker2 = resolvePoker(playerPoker2);
//        for(int i = 0; i<)
        return null;
    }

    public List<Character> resolvePoker(String poker){
        String[] pokers = poker.split("");
        List<Character> pokerList = Arrays.stream(pokers).map(element -> element.charAt(0)).collect(Collectors.toList());
        return pokerList;
    }
}
