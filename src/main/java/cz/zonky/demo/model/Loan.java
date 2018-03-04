package cz.zonky.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Loan extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
    private String url;
    
    private String name;
    private String story;
    private Integer purpose;

    private List<Photo> photos = new ArrayList<>();
    private Long userId;
    private String nickName;
    private Integer termInMonths;
    private BigDecimal interestRate;
    private String rating;
    private Boolean topped;
    private BigDecimal amount;
    private BigDecimal remainingInvestment;
    private BigDecimal investmentRate;
    private boolean covered;
    
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)  
    private LocalDateTime datePublished;
    
    private boolean published;
    
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deadline;
    private String myOtherInvestments;
    private String borrowerRelatedInvestmentInfo;
    private int investmentsCount;
    private int questionsCount;
    private Integer region;
    private IncomeTypeEnum mainIncomeType;
    private boolean questionsAllowed;
    private int activeLoansCount;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos.clear();
		if(photos != null) {
			this.photos.addAll(photos);
		}
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getTermInMonths() {
		return termInMonths;
	}
	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Boolean getTopped() {
		return topped;
	}
	public void setTopped(Boolean topped) {
		this.topped = topped;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getRemainingInvestment() {
		return remainingInvestment;
	}
	public void setRemainingInvestment(BigDecimal remainingInvestment) {
		this.remainingInvestment = remainingInvestment;
	}
	public BigDecimal getInvestmentRate() {
		return investmentRate;
	}
	public void setInvestmentRate(BigDecimal investmentRate) {
		this.investmentRate = investmentRate;
	}
	public boolean isCovered() {
		return covered;
	}
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	public LocalDateTime getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(LocalDateTime datePublished) {
		this.datePublished = datePublished;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public String getMyOtherInvestments() {
		return myOtherInvestments;
	}
	public void setMyOtherInvestments(String myOtherInvestments) {
		this.myOtherInvestments = myOtherInvestments;
	}
	public String getBorrowerRelatedInvestmentInfo() {
		return borrowerRelatedInvestmentInfo;
	}
	public void setBorrowerRelatedInvestmentInfo(
			String borrowerRelatedInvestmentInfo) {
		this.borrowerRelatedInvestmentInfo = borrowerRelatedInvestmentInfo;
	}
	public int getInvestmentsCount() {
		return investmentsCount;
	}
	public void setInvestmentsCount(int investmentsCount) {
		this.investmentsCount = investmentsCount;
	}
	public int getQuestionsCount() {
		return questionsCount;
	}
	public void setQuestionsCount(int questionsCount) {
		this.questionsCount = questionsCount;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public IncomeTypeEnum getMainIncomeType() {
		return mainIncomeType;
	}
	public void setMainIncomeType(IncomeTypeEnum mainIncomeType) {
		this.mainIncomeType = mainIncomeType;
	}
	public boolean isQuestionsAllowed() {
		return questionsAllowed;
	}
	public void setQuestionsAllowed(boolean questionsAllowed) {
		this.questionsAllowed = questionsAllowed;
	}
	public int getActiveLoansCount() {
		return activeLoansCount;
	}
	public void setActiveLoansCount(int activeLoansCount) {
		this.activeLoansCount = activeLoansCount;
	}
	
	public static class Builder{
		private Loan loan;
		public Builder(Long id) {
			loan = new Loan();
			loan.setId(id);
		}
		
		public Builder url(String url) {
			loan.setUrl(url);
			return this;
		}
		
		public Builder name(String name) {
			loan.setName(name);
			return this;
		}
		public Builder story(String story) {
			loan.setStory(story);
			return this;
		}
		public Builder purpose(Integer purpose) {
			loan.setPurpose(purpose);
			return this;
		}
		
		// ... other properties

		public Loan build() {
			return loan;
		}		
	}
	
	@Override
	protected Object[] getBaseFields() {
		return new Object[]{id};
	}

	
}