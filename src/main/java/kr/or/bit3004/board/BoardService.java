package kr.or.bit3004.board;

import java.util.List;

public interface BoardService {
	//게시판 목록보기
	public List<Board> selectBoardList(int allBoardListNo);
	
	//게시판 상세보기
	public Board selectBoardByBoardNo(int boardNo);
	
	//게시판 조회수 증가
	public void updateReadCount(int boardNo);
	
	//게시판 글쓰기
	public void insertBoard(Board board);
	
	//파일 업로드
	//public void fileUploadBoard(Board board , HttpServletRequest request);
	
	//게시판 수정하기
	public void updateBoard(Board board);
	
	//게시판 삭제하기
	public void deleteBoard(int boardNo);
	
	//게시판 답글쓰기
	public void insertReboard(Board board);

}
