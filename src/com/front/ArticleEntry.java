package com.front;

import java.lang.IllegalArgumentException;

/**
 * Represents a single query record for the main search server
 * @author Nicholas Carugati
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class ArticleEntry {

	/**
	 * The data member attributes for a single record
	 */
	private String mdate, key, author, title, pages, year, volume, journal, number, url, ee;
	
	/**
	 * Determines if the type of record 
	 * is a article entry or a committee entry
	 */
	private boolean isPaper = true;
	
	/**
	 * The name of the committee
	 */
	private String commName;
	
	/**
	 * The type of member the person is
	 */
	private MemberType memberType;
	
	/**
	 * The year when the committee took place
	 */
	private int commYear = 0;

	/**
	 * A setter to change the type of record
	 * @param isPaper if the record should be an article entry or committee entry
	 */
	public void setIsPaper(boolean isPaper) {
		this.isPaper = isPaper;
	}

	/**
	 * Sets the Mdate of the record
	 * @param mdate the mdate
	 */
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	/**
	 * Retrieves the Mdate of the current record
	 * @return the current Mdate
	 */
	public String getMdate() {
		return mdate;
	}

	/**
	 * Retrieves the key string of a record
	 * @return the key string
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Retrives the author of a record
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Retrieves the title of a record
	 * @return the title of the article
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Retrieves the pages of the record
	 * @return the pages in the article
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * Gets the year of an article
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Gets the volume number of a article within a record
	 * @return the volume number
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * Gets the journal information of the record
	 * @return the journal data
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * Retrieves the unique number of the record
	 * @return the unique number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Gets the URL of the article 
	 * @return the URL of the article
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Retrieves the Electronic data of the article
	 * @return the EE data of the article
	 */
	public String getEe() {
		return ee;
	}

	/**
	 * Sets the key of a record
	 * @param key the new key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the author of a record
	 * @param author the new author name to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Sets the title of a record
	 * @param title the new title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the pages of a record
	 * @param pages the new page length
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}

	/**
	 * Sets the year of the article
	 * @param year the new year for the article
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Sets the volume of the article
	 * @param volume the new volume information
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * Sets the journal information about the article
	 * @param journal the new journal information
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}

	/**
	 * Sets the unique number information for a record
	 * @param number the new number information
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Sets a new URL location for the article
	 * @param url the new URL to point to.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Sets the eletronic data of the record
	 * @param ee the new EE data
	 */
	public void setEe(String ee) {
		this.ee = ee;
	}

	/**
	 * Sets the committee member type of a record (If applicable)
	 * @param memberType the new member type
	 */
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	/**
	 * Sets a member type based on a string input
	 * @param memberTypeString the string input
	 */
	public void setMemberType(String memberTypeString) {
		if (memberTypeString == null || memberTypeString.isEmpty())
			this.memberType = MemberType.Member;
		else {
			try {
				this.memberType = MemberType.valueOf(memberTypeString);
			} catch (IllegalArgumentException ex) {
				this.memberType = MemberType.Member;
			}
		}

	}

	/**
	 * Sets the committee name for a record
	 * @param commName the new committee name
	 */
	public void setCommitteeName(String commName) {
		this.commName = commName.toUpperCase();
	}

	/**
	 * Sets the committee year for a record
	 * @param commYear the new year for the committee.
	 */
	public void setCommitteeYear(String commYear) {
		try {
			this.commYear = Integer.parseInt(commYear);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves the committee name for a record
	 * @return the committee name
	 */
	public String getCommitteeName() {
		return this.commName;
	}

	/**
	 * Retrieves the committee name for a record
	 * @return the committee year
	 */
	public Integer getCommitteeYear() {
		return this.commYear;
	}

	/**
	 * Retrieves the member type of a committee record
	 * @return the member type
	 */
	public MemberType getMemberType() {
		return this.memberType;
	}

	/**
	 * Converts the instance to a string
	 * @returns a string representation of the instance
	 */
	@Override
	public String toString() {

		String payload = "";
		if (isPaper) {
			payload = "[mdate " + mdate + " key " + key + " author " + author + " title " + title + " pages " + pages
					+ " year " + year + " volume " + volume + " journal " + journal + " number " + number + " url "
					+ url + " ee " + ee + "]";
		} else {
			payload = "[memberName " + this.author + " commName " + this.commName + " memType " + this.memberType
					+ " commYear " + this.commYear + "]";
		}
		return payload;
	}

}
