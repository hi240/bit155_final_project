package kr.or.bit3004.dao;

import java.util.List;
import java.util.Map;

import kr.or.bit3004.groupAndTeam.GroupAndTeam;
import kr.or.bit3004.groupAndTeam.Team;
import kr.or.bit3004.groupAndTeam.TeamMember;

public interface TeamMainDao {
	public List<GroupAndTeam> getGroup(String id);
	public List<GroupAndTeam> getGroupAndTeam(String id);
	public List<GroupAndTeam> getTeamMemberList(String id);
	public int getCurrGroupNo();
	public void updateGroupName(GroupAndTeam group);
	public void insertGroup(GroupAndTeam group);
	public int searchPersonalNo(String id);
	public void moveGroup(Map<String, Integer> groupNo);
	public void delGroup(int groupNo);
	public void insertTeam(GroupAndTeam team);
	public void insertTeamLeader(GroupAndTeam team);
	public void insertGroupTeam(GroupAndTeam team);
	public int getCurrTeamNo();
	public void moveTeamFromGroup(GroupAndTeam groupAndTeam);
	public Team getTeam(int teamNo);
	public List<TeamMember> getTeamMember(int teamNo);
	
}
