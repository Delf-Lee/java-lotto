package domain;

import exception.DuplicatedLottoNumberException;
import exception.LottoNumberException;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) throws IllegalArgumentException {
        checkArguments(lotto, bonusNo);

        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void checkArguments(Lotto lotto, int bonusNo) throws IllegalArgumentException {
        if (bonusNo < Lotto.MIN_NUM || bonusNo > Lotto.MAX_NUM) {
            throw new LottoNumberException();
        }

        if (lotto.contains(bonusNo)) {
            throw new DuplicatedLottoNumberException();
        }
    }

    public Rank match(Lotto userLotto) {
        int matchCount = lotto.getMatchCount(userLotto);
        boolean bonus = userLotto.contains(bonusNo);
        return Rank.valueOf(matchCount, bonus);
    }

}
