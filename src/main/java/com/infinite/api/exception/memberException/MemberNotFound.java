package com.infinite.api.exception.memberException;

import com.infinite.api.exception.BaseException;
import com.infinite.api.exception.memberException.memberEnum.MemberEnum;

public class MemberNotFound extends BaseException {

    public MemberNotFound(MemberEnum memberEnum) {
        super(memberEnum.getMessage());
    }

    @Override
    public int getStatusCode() {
        return MemberEnum.MEMBER_NOT_FOUND.getCode();
    }
}
