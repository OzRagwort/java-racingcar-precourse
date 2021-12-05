package racingcar.view;

import static camp.nextstep.edu.missionutils.Console.*;
import static racingcar.view.OutputView.*;

import racingcar.domain.CarNames;

public class InputView {
	public static String[] readCarNames() {
		printReadCarNamesMessage();
		String[] names = readLine().split(",");
		return (new CarNames(names)).get();
	}
}
