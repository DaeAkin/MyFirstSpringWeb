package com.min.www.util;

import java.util.Comparator;

import com.min.www.dto.BoardReplyDto;

public class SocketReplySort implements Comparator<BoardReplyDto>{

	@Override
	public int compare(BoardReplyDto o1, BoardReplyDto o2) {
		// TODO Auto-generated method stub
		return o1.getRegister_datetime().compareTo(o2.getRegister_datetime());
	}

}
