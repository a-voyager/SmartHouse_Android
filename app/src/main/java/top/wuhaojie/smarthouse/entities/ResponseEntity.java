package top.wuhaojie.smarthouse.entities;

import java.util.List;

/**
 * Created by wuhaojie on 2016/7/7 14:03.
 */
public class ResponseEntity {


    /**
     * mIsError : false
     * mErrorType : 0
     * mErrorMessage :
     * mInfoItems : [{"mId":7501,"mNodeId":0,"mTemperature":"11","mHumidity":"16","mWindSpeed":"","mWindDirection":"","mCurtainState":"","mIsSafe":"","mSmoke":"","mUltrasonicWave":"","mTimeStamp":1467800797000}]
     */

    private boolean mIsError;
    private int mErrorType;
    private String mErrorMessage;
    /**
     * mId : 7501
     * mNodeId : 0
     * mTemperature : 11
     * mHumidity : 16
     * mWindSpeed :
     * mWindDirection :
     * mCurtainState :
     * mIsSafe :
     * mSmoke :
     * mUltrasonicWave :
     * mTimeStamp : 1467800797000
     */

    private List<MInfoItemsBean> mInfoItems;

    public boolean isMIsError() {
        return mIsError;
    }

    public void setMIsError(boolean mIsError) {
        this.mIsError = mIsError;
    }

    public int getMErrorType() {
        return mErrorType;
    }

    public void setMErrorType(int mErrorType) {
        this.mErrorType = mErrorType;
    }

    public String getMErrorMessage() {
        return mErrorMessage;
    }

    public void setMErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public List<MInfoItemsBean> getMInfoItems() {
        return mInfoItems;
    }

    public void setMInfoItems(List<MInfoItemsBean> mInfoItems) {
        this.mInfoItems = mInfoItems;
    }

    public static class MInfoItemsBean {
        private int mId;
        private int mNodeId;
        private String mTemperature;
        private String mHumidity;
        private String mWindSpeed;
        private String mWindDirection;
        private String mCurtainState;
        private String mIsSafe;
        private String mSmoke;
        private String mUltrasonicWave;
        private long mTimeStamp;

        public int getMId() {
            return mId;
        }

        public void setMId(int mId) {
            this.mId = mId;
        }

        public int getMNodeId() {
            return mNodeId;
        }

        public void setMNodeId(int mNodeId) {
            this.mNodeId = mNodeId;
        }

        public String getMTemperature() {
            return mTemperature;
        }

        public void setMTemperature(String mTemperature) {
            this.mTemperature = mTemperature;
        }

        public String getMHumidity() {
            return mHumidity;
        }

        public void setMHumidity(String mHumidity) {
            this.mHumidity = mHumidity;
        }

        public String getMWindSpeed() {
            return mWindSpeed;
        }

        public void setMWindSpeed(String mWindSpeed) {
            this.mWindSpeed = mWindSpeed;
        }

        public String getMWindDirection() {
            return mWindDirection;
        }

        public void setMWindDirection(String mWindDirection) {
            this.mWindDirection = mWindDirection;
        }

        public String getMCurtainState() {
            return mCurtainState;
        }

        public void setMCurtainState(String mCurtainState) {
            this.mCurtainState = mCurtainState;
        }

        public String getMIsSafe() {
            return mIsSafe;
        }

        public void setMIsSafe(String mIsSafe) {
            this.mIsSafe = mIsSafe;
        }

        public String getMSmoke() {
            return mSmoke;
        }

        public void setMSmoke(String mSmoke) {
            this.mSmoke = mSmoke;
        }

        public String getMUltrasonicWave() {
            return mUltrasonicWave;
        }

        public void setMUltrasonicWave(String mUltrasonicWave) {
            this.mUltrasonicWave = mUltrasonicWave;
        }

        public long getMTimeStamp() {
            return mTimeStamp;
        }

        public void setMTimeStamp(long mTimeStamp) {
            this.mTimeStamp = mTimeStamp;
        }

        @Override
        public String toString() {
            return "MInfoItemsBean{" +
                    "mId=" + mId +
                    ", mNodeId=" + mNodeId +
                    ", mTemperature='" + mTemperature + '\'' +
                    ", mHumidity='" + mHumidity + '\'' +
                    ", mWindSpeed='" + mWindSpeed + '\'' +
                    ", mWindDirection='" + mWindDirection + '\'' +
                    ", mCurtainState='" + mCurtainState + '\'' +
                    ", mIsSafe='" + mIsSafe + '\'' +
                    ", mSmoke='" + mSmoke + '\'' +
                    ", mUltrasonicWave='" + mUltrasonicWave + '\'' +
                    ", mTimeStamp=" + mTimeStamp +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "mIsError=" + mIsError +
                ", mErrorType=" + mErrorType +
                ", mErrorMessage='" + mErrorMessage + '\'' +
                ", mInfoItems=" + mInfoItems +
                '}';
    }
}
