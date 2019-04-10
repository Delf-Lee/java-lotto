package ui;

import domain.Lotto;
import domain.Rank;
import javafx.scene.control.RadioMenuItem;

import java.util.*;

/**
 * @author delf
 */
public class ConsoleIOIneterface implements LottoIOInterface {

    private static final Scanner scanner = new Scanner(System.in);
    private final static String DELIM = ",";

    private final static String MESSAGE_INPUT_MONEY = "구입금액을 입력헤주세요";
    private final static String MESSAGE_INFO_BUY_LOTTO_COUNT = "개를 구매했습니다.";
    private final static String MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해주세요.";
    private final static String TITLE_WINNING_STATISTICS = "당첨 통계" + "\n" + "-------------";

    private final static Rank[] PRINT_ORDER_RANK = {Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST};

    @Override
    public int insertMoney() {
        LottoIOInterface.showPlaneText(MESSAGE_INPUT_MONEY);

        return inputInteger();
    }

    @Override
    public List<Integer> inputWinningLottoNumbers() {
        LottoIOInterface.showPlaneText("\n" + MESSAGE_INPUT_WINNING_NUMBER);
        String input = scanner.nextLine();
        String[] splitted = input.split(DELIM);

        return convertToIntegerList(splitted);
    }

    private List<Integer> convertToIntegerList(String[] splitted) {
        List<Integer> list = new ArrayList<>(splitted.length);

        for (String s : splitted) {
            list.add(Integer.valueOf(s));
        }

        return list;
    }

    @Override
    public int inputBounusNumber(Lotto lotto) {
        LottoIOInterface.showPlaneText(MESSAGE_INPUT_BONUS_NUMBER);

        return inputInteger();
    }

    @Override
    public void showLottos(List<Lotto> lottos) {
        LottoIOInterface.showPlaneText("\n" + lottos.size() + MESSAGE_INFO_BUY_LOTTO_COUNT);
        for (Lotto lotto : lottos) {
            LottoIOInterface.showPlaneText(lotto.toString());
        }
    }

    @Override
    public void showWinningStatistics(Map<Rank, Integer> ranks) {
        LottoIOInterface.showPlaneText(TITLE_WINNING_STATISTICS);

        for (Rank rank : PRINT_ORDER_RANK) {
            String result = rank + " - " + ranks.get(rank) + "개";
            LottoIOInterface.showPlaneText(result);
        }
    }

    private int inputInteger() {
        int n = scanner.nextInt();
        scanner.nextLine(); // 버퍼에 남아 있는 개행 제거
        return n;
    }
}
