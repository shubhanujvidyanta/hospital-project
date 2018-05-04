package personal.shubhanuj.hospital.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Member {
	private int memberId;
	private int mbrroleId;
	private String joinDate;
	private String leaveDate;
	
	public Member(int a,int b){
		memberId=a;
		mbrroleId=b;
		joinDate=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		leaveDate=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the joinDate
	 */
	public String getJoinDate() {
		return joinDate;
	}
	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	/**
	 * @return the leaveDate
	 */
	public String getLeaveDate() {
		return leaveDate;
	}
	/**
	 * @param leaveDate the leaveDate to set
	 */
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	/**
	 * @return the mbrroleId
	 */
	public int getMbrroleId() {
		return mbrroleId;
	}
	/**
	 * @param mbrroleId the mbrroleId to set
	 */
	public void setMbrroleId(int mbrroleId) {
		this.mbrroleId = mbrroleId;
	}
}
