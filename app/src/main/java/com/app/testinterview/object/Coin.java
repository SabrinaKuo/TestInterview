package com.app.testinterview.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuosabrina on 2018/1/10.
 */

public class Coin {

    /**
     * BonusArray : [{"Title":"FB","Value":18.09},{"Title":"Register","Value":5701},{"Title":"AP","Value":230},{"Title":"TP1","Value":13085.58},{"Title":"MP","Value":0},{"Title":"Red","Value":0},{"Title":"Black","Value":9900},{"Title":"DRAmount","Value":0},{"Title":"CRAmount","Value":0},{"Title":"DMAmount","Value":0},{"Title":"BonusAmount","Value":0}]
     * ResultCode : 0000
     * ResultMessage : 成功
     */

    private String ResultCode;
    private String ResultMessage;
    private ArrayList<BonusArrayBean> BonusArray;

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }

    public ArrayList<BonusArrayBean> getBonusArray() {
        return BonusArray;
    }

    public void setBonusArray(ArrayList<BonusArrayBean> BonusArray) {
        this.BonusArray = BonusArray;
    }

    public static class BonusArrayBean {
        /**
         * Title : FB
         * Value : 18.09
         */

        private String Title;
        private double Value;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public double getValue() {
            return Value;
        }

        public void setValue(double Value) {
            this.Value = Value;
        }
    }
}
