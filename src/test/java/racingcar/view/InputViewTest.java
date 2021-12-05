package racingcar.view;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.utils.CustomNsTest;

public class InputViewTest extends CustomNsTest {
	private static final String CORRECT_CAR_NAMES = "ozr,rag,wort";
	private static final String INVALID_DUPLICATE_CAR_NAMES = "ozr,rag,ozr";
	private static final String INVALID_LONG_LENGTH_CAR_NAMES = "ozr,ragwort";
	private static final String INVALID_EMPTY_LENGTH_CAR_NAMES = "ozr,,wort";

	private static final String ERROR_MESSAGE = "[ERROR]";

	@DisplayName("차의 이름을 받아오는 테스트")
	@Test
	public void readCarNamesTest() {
		command(CORRECT_CAR_NAMES);
		String[] testValues = CORRECT_CAR_NAMES.split(",");
		String[] inputs = InputView.readCarNames();
		for (int i = 0; i < testValues.length; i++) {
			assertEquals(inputs[i], testValues[i]);
		}
	}

	@DisplayName("예외 처리 후 재입력 테스트")
	@Test
	public void readCarNamesReloadTest() {
		command(INVALID_LONG_LENGTH_CAR_NAMES, CORRECT_CAR_NAMES);
		String[] testValues = CORRECT_CAR_NAMES.split(",");
		String[] inputs = InputView.readCarNames();
		for (int i = 0; i < testValues.length; i++) {
			assertEquals(inputs[i], testValues[i]);
		}
	}

	@DisplayName("자동차 이름 중복 입력 예외 테스트")
	@Test
	public void readCarNamesDuplicateExceptionTest() {
		command(INVALID_DUPLICATE_CAR_NAMES);
		try {
			InputView.readCarNames();
		} catch (NoSuchElementException ignored) {
		}
		assertThat(output()).contains(ERROR_MESSAGE);
	}

	@DisplayName("자동차 이름 길이 초과 예외 테스트")
	@Test
	public void readCarNamesLongLengthExceptionTest() {
		command(INVALID_LONG_LENGTH_CAR_NAMES);
		try {
			InputView.readCarNames();
		} catch (NoSuchElementException ignored) {
		}
		assertThat(output()).contains(ERROR_MESSAGE);
	}

	@DisplayName("자동차 이름 빈칸 입력 예외 테스트")
	@Test
	public void readCarNamesExceptionTest() {
		command(INVALID_EMPTY_LENGTH_CAR_NAMES);
		try {
			InputView.readCarNames();
		} catch (NoSuchElementException ignored) {
		}
		assertThat(output()).contains(ERROR_MESSAGE);
	}

	@Override
	protected void runMain() {
	}
}
