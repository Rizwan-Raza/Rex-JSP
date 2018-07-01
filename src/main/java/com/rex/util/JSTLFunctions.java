package com.rex.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.rex.bean.UserBean;

public class JSTLFunctions {

	public static String daysUntilToday(Date date) {
		long diff = new Date().getTime() - date.getTime();
		if (diff < 2.628e+9) {
			if (diff < 8.64e+7) {
				if (diff < 3.6e+6) {
					if (diff < 60000) {
						return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS) + " seconds ago";
					} else {
						return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS) + " minute"
								+ ((diff > 120000) ? "s" : "") + " ago";
					}
				} else {
					return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) + " hour" + ((diff > 7.2e+6) ? "s" : "")
							+ " ago";
				}
			} else {
				return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " day" + ((diff > 1.728e+8) ? "s" : "")
						+ " ago";
			}
		} else {
			return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 30 + " month" + ((diff > 5.256e+9) ? "s" : "")
					+ " ago";
		}
	}

	public static boolean isLiker(ArrayList<UserBean> list, UserBean user) {
		for (UserBean ub : list) {
			if (ub.getUid() == user.getUid()) {
				return true;
			}
		}
		return false;
	}
}
