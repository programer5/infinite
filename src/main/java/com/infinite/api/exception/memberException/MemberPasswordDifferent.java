package com.infinite.api.exception.memberException;

import com.infinite.api.exception.BaseException;
import com.infinite.api.exception.memberException.memberEnum.MemberEnum;

public class MemberPasswordDifferent extends BaseException {

    public MemberPasswordDifferent(MemberEnum memberEnum) {
        super(memberEnum.getMessage());
    }

    @Override
    public String getStatusCode() {
        return MemberEnum.MEMBER_NOT_FOUND.getCode();
    }
}
