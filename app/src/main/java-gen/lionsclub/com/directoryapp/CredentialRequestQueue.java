package lionsclub.com.directoryapp;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table CREDENTIAL_REQUEST_QUEUE.
 */
public class CredentialRequestQueue {

    private String dateTimeRequested;
    private Boolean isProcessed;
    private String dateTimeProcessed;
    private long memInt;

    public CredentialRequestQueue() {
    }

    public CredentialRequestQueue(String dateTimeRequested, Boolean isProcessed, String dateTimeProcessed, int memInt) {
        this.dateTimeRequested = dateTimeRequested;
        this.isProcessed = isProcessed;
        this.dateTimeProcessed = dateTimeProcessed;
        this.memInt = memInt;
    }

    public String getDateTimeRequested() {
        return dateTimeRequested;
    }

    public void setDateTimeRequested(String dateTimeRequested) {
        this.dateTimeRequested = dateTimeRequested;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getDateTimeProcessed() {
        return dateTimeProcessed;
    }

    public void setDateTimeProcessed(String dateTimeProcessed) {
        this.dateTimeProcessed = dateTimeProcessed;
    }

    public long getMemInt() {
        return memInt;
    }

    public void setMemInt(long memInt) {
        this.memInt = memInt;
    }

}