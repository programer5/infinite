package com.infinite.api.exception.memberException;

import com.infinite.api.exception.BaseException;
import com.infinite.api.exception.memberException.memberEnum.MemberEnum;

public class AlreadyExistsMemberException extends BaseException {

    public AlreadyExistsMemberException(MemberEnum memberEnum) {
        super(memberEnum.getMessage());
    }

    @Override
    public String getStatusCode() {
        return MemberEnum.ALREADY_EXISTS_MEMBER.getCode();
    }
}
