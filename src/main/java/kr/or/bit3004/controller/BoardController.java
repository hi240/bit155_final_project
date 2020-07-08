package kr.or.bit3004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit3004.dto.Board;
import kr.or.bit3004.service.BoardService;

@Controller
//@RequestMapping
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//게시판 목록
	@RequestMapping("boardList.do")
	public String selectBoardListService(Model model, int allBoardListNo) {
		model.addAttribute("boardList", service.selectBoardList(allBoardListNo));
		return "board/list";
	}
	
	//게시판 상세보기
	@RequestMapping("selectBoard.do")
	public String selectBoardByBoardNoService(Model model, int no) {
		model.addAttribute("selectBoard", service.selectBoardByBoardNo(no));
		return "board/detail";
	}
	
	//게시판 글쓰기(폼)
	@RequestMapping(value = "insertBoard.do" , method = RequestMethod.GET)
	public String insertBoardService() {
		return "board/insert";
	}
	
	//게시판 글쓰기
	@RequestMapping(value = "insertBoard.do" , method = RequestMethod.POST)
	public String insertBoardService(Board board){
		service.insertBoard(board);
		return "redirect:boardList.do?allBoardListNo=1";
	}
	
	//게시판 수정하기(폼)
	@RequestMapping(value = "updateBoard.do" , method = RequestMethod.GET)
	public String updateBoard(int no , Model model) {
		model.addAttribute("board" , service.selectBoardByBoardNo(no));
		return "board/update";
	}
	//게시판 수정하기
	@RequestMapping(value = "updateBoard.do" , method = RequestMethod.POST)
	public String updateBoard(Board board) {
		System.out.println(board);
		service.updateBoard(board);
		return "redirect:boardList.do?allBoardListNo=1";
	}
	
	//게시판 삭제하기
	@RequestMapping("deleteBoard.do")
	public String deleteBoard(int no) {
		service.deleteBoard(no);
		return "redirect:boardList.do?allBoardListNo=1";
	}
}
 