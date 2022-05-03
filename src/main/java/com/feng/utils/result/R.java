//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.feng.utils.result;

public class R {
    public R() {
    }

    public static Result<String> success() {
        return new Result(true, "成功");
    }

    public static <T> Result<T> success(T data) {
        return new Result(data, true, "成功");
    }

    public static Result<String> fail() {
        return new Result(false, "未知错误，联系管理员");
    }

    public static Result<String> fail(String message) {
        return new Result(false, message);
    }
}
