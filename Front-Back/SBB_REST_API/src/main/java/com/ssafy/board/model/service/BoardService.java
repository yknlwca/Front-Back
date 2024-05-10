package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;

public interface BoardService {
	// 게시글 전체 조회 및 삭제
	public List<Board> searchBoard(SearchCondition searchCondition);

	// 게시글 상세조회
	public Board readBoard(int id);

	// 게시글 작성
	public boolean writeBoard(Board board);

	/// 게시글 삭제
	public boolean removeBoard(int id);

	// 게시글 수정
	public boolean modifyBoard(Board board);
}
