package fun7App.features;

import fun7App.utilities.ApplicationConstants;


import java.time.*;

public class CustumerSupport {
    private String customerSupportResponse;

    public CustumerSupport(LocalDateTime localDateTime) {
        this.customerSupportResponse = checkCustomerSupportWorkingTime(localDateTime);
    }

    public String checkCustomerSupportWorkingTime(LocalDateTime localDateTime) {
        if (ApplicationConstants.START_OF_CUSTOMER_SERVICE < localDateTime.getHour() && localDateTime.getHour() < ApplicationConstants.END_OF_CUSTOMER_SERVICE && localDateTime.getDayOfWeek() != DayOfWeek.SATURDAY
                && localDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
            return ApplicationConstants.SERVICE_ENABLED;
        }
        return ApplicationConstants.SERVICE_DISABLED;
    }

    public String getCustomerSupportResponse() {
        return customerSupportResponse;
    }

    public void setCustomerSupportResponse(String supportEnabled) {
        customerSupportResponse = supportEnabled;
    }
}
