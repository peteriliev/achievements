package com.iliev.peter.servlet;

import static com.iliev.peter.servlet.Params.*;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.iliev.peter.achieve.ARecordStatus;

final class Util {
	private Util() {
		throw new RuntimeException("Trying to instantiate Util class");
	}

	static final UUID getUUIDParam(final HttpServletRequest req, final String param) {
		if (null == req) {
			throw new IllegalArgumentException("Illegal HttpServletRequest: NULL");
		}

		if (StringUtils.isBlank(param)) {
			throw new IllegalArgumentException("Illegal param: blank");
		}

		final String strUUID = req.getParameter(param);

		if (StringUtils.isBlank(strUUID)) {
			throw new IllegalArgumentException(String.format("Illegal HttpServletRequest value: %s is blank", param));
		}

		return UUID.fromString(strUUID);
	}

	static final void setJSONStatus(final HttpServletResponse resp, final ARecordStatus status) throws JSONException, IOException {
		if (null == resp) {
			throw new IllegalArgumentException("Illegal HttpServletResponse: NULL");
		}

		if (null == status) {
			throw new IllegalArgumentException("Illegal ARecordStatus: null");
		}

		JSONObject json = new JSONObject();
		json.put(RECORD_STATUS, String.valueOf(status));

		resp.setContentType("application/json");
		resp.getWriter().print(json);
	}
}