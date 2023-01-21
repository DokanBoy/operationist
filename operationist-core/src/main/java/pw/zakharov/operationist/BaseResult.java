package pw.zakharov.operationist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResult implements Result {

    private final boolean success;

    public static Result ok() {
        return new BaseResult(true);
    }

    public static Result failed() {
        return new BaseResult(false);
    }

    @Override
    public boolean success() {
        return success;
    }

}
