package kr.or.bit3004.kanban;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KanbanController {
	
	@Autowired
	private KanbanService service;
	
	@RequestMapping("/kanban.do")
	public String kanbanList(int allBoardListNo, Model model) {
		List<Map> kanbanlist = new ArrayList<>();
		List<Map> kanbancardlist = new ArrayList<>();
		
		kanbanlist = service.allKanbanList(allBoardListNo);
		kanbancardlist = service.kanbanCardList();
		model.addAttribute("kanbanlist",kanbanlist);
		model.addAttribute("kanbancardlist",kanbancardlist);
		System.out.println(kanbanlist);
		System.out.println(kanbancardlist);
		return "kanban/kanban";
	}
	
	
	
	@RequestMapping("InsertKanbanList.ajax")
	public String kanbanListInsert(KanbanList kanbanlist) {
		System.out.println("listinsert");
		service.insertListTite(kanbanlist);
		return "redirect:kanban.do?teamNo=6";
	}
	
	
	@RequestMapping("UpdateKanbanList.ajax")
	public String kanbanListUpdate(int allBoardListNo, KanbanList kanbanlist) {
		System.out.println("listupdate???");
		List<Map> alllist = new ArrayList<>();
		List<String> kanbanlistno = new ArrayList<String>();
		
		alllist = service.allKanbanList(allBoardListNo);
		for(int i = 0; i < alllist.size(); i++) {
			kanbanlistno.add(alllist.get(i).get("kanbanListNo").toString());
		}
		
		service.updateListTite(kanbanlist);
		return "redirect:kanban.do?teamNo=6";
	}
}
 