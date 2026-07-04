package com.bcs.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class CountDetails {

	@Id
	private int Id;
	
	@Transient
	private Long totalVisit;
		
	@Transient
	private Long todaysVisit;
	
	@Transient
	private Long totalFollowup;
		
	@Transient
	private Long todaysFollowups;
	
	@Transient
	private Long totalClosedVisits;
	
	@Transient
	private Long todaysClosedVisits;
	
	@Transient
	private Long totalDemandLetters;
	
	@Transient
	private Long todaysSendDemandLetters;	
	
	@Transient
	private Long totalClient;
	
	@Transient
	private Long todaysClients;
	
	@Transient
	private Long totalPaymentFollowupCount;
	
	@Transient
	private Long todaysPaymentFollowupCount;
	
	@Transient
	private Long todaysReceivedPaymentCount;

	public CountDetails(){
		
	}	
	

	public Long getTotalVisit() {
		return totalVisit;
	}

	public void setTotalVisit(Long totalVisit) {
		this.totalVisit = totalVisit;
	}

	public Long getTodaysVisit() {
		return todaysVisit;
	}

	public void setTodaysVisit(Long todaysVisit) {
		this.todaysVisit = todaysVisit;
	}

	public Long getTotalFollowup() {
		return totalFollowup;
	}

	public void setTotalFollowup(Long totalFollowup) {
		this.totalFollowup = totalFollowup;
	}

	public Long getTodaysFollowups() {
		return todaysFollowups;
	}

	public void setTodaysFollowups(Long todaysFollowups) {
		this.todaysFollowups = todaysFollowups;
	}

	public Long getTotalClosedVisits() {
		return totalClosedVisits;
	}

	public void setTotalClosedVisits(Long totalClosedVisits) {
		this.totalClosedVisits = totalClosedVisits;
	}

	public Long getTodaysClosedVisits() {
		return todaysClosedVisits;
	}

	public void setTodaysClosedVisits(Long todaysClosedVisits) {
		this.todaysClosedVisits = todaysClosedVisits;
	}

	public Long getTotalDemandLetters() {
		return totalDemandLetters;
	}

	public void setTotalDemandLetters(Long totalDemandLetters) {
		this.totalDemandLetters = totalDemandLetters;
	}

	public Long getTodaysSendDemandLetters() {
		return todaysSendDemandLetters;
	}

	public void setTodaysSendDemandLetters(Long todaysSendDemandLetters) {
		this.todaysSendDemandLetters = todaysSendDemandLetters;
	}

	public Long getTotalClient() {
		return totalClient;
	}

	public void setTotalClient(Long totalClient) {
		this.totalClient = totalClient;
	}

	public Long getTodaysClients() {
		return todaysClients;
	}

	public void setTodaysClients(Long todaysClients) {
		this.todaysClients = todaysClients;
	}

	public Long getTotalPaymentFollowupCount() {
		return totalPaymentFollowupCount;
	}


	public void setTotalPaymentFollowupCount(Long totalPaymentFollowupCount) {
		this.totalPaymentFollowupCount = totalPaymentFollowupCount;
	}


	public Long getTodaysPaymentFollowupCount() {
		return todaysPaymentFollowupCount;
	}

	public void setTodaysPaymentFollowupCount(Long todaysPaymentFollowupCount) {
		this.todaysPaymentFollowupCount = todaysPaymentFollowupCount;
	}

	public Long getTodaysReceivedPaymentCount() {
		return todaysReceivedPaymentCount;
	}

	public void setTodaysReceivedPaymentCount(Long todaysReceivedPaymentCount) {
		this.todaysReceivedPaymentCount = todaysReceivedPaymentCount;
	}

	
}
