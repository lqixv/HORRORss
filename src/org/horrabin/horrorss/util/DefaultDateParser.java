/**
 * DefaultDateParser.java 
 *
 * HORRORss Package, Version 2.2.0
 * Simple RSS parser
 *
 * October 16, 2012
 *
 * Copyright (C) 2012 Fernando Fornieles
 * e-mail: nandofm@gmail.com
 *
 * This file is part of HORRORss
 *
 * HORRORss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HORRORss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.horrabin.horrorss.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.horrabin.horrorss.RssParser;

public class DefaultDateParser implements DateParser {
	DateFormat sd = null;

	public Date getDate(String date, int rssType) throws Exception {
		Date res = null;
		String pattern = null;

		switch (rssType) {
		case RssParser.TYPE_RDF: {
			if (date.indexOf("+") >= 0)
				pattern = "yyyy-MM-dd'T'HH:mm:ss+HH:mm";
			else
				pattern = "yyyy-MM-dd'T'HH:mm:ss-HH:mm";
			break;
		}
		case RssParser.TYPE_RSS: {
			pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
			break;
		}
		case RssParser.TYPE_ATOM: {
			pattern = "yyyy-MM-dd'T'HH:mm:ss";
			break;
		}
		}

		boolean success = false;
		try {
			if (sd == null)
				sd = new SimpleDateFormat(pattern, Locale.ENGLISH);
			res = sd.parse(date);
			success = true;
		} catch (Exception e) {
		}

		if(!success){
			try {
				pattern = "yyyy-MM-dd'T'HH:mm:ss+HH:mm";
				sd = new SimpleDateFormat(pattern);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "yyyy-MM-dd'T'HH:mm:ss-HH:mm";
				sd = new SimpleDateFormat(pattern);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				sd = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				sd = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				sd = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				sd = DateFormat.getDateInstance(DateFormat.FULL);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "EEE, dd MMM yyyy HH:mm:ss";
				sd = new SimpleDateFormat(pattern, Locale.ENGLISH);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "yyyy-MM-dd'T'HH:mm:ss";
				sd = new SimpleDateFormat(pattern);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "yyyy-MM-dd'T'HH:mm:ss.Z";
				sd = new SimpleDateFormat(pattern, Locale.ENGLISH);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "yyyy-MM-dd HH:mm:ss";
				sd = new SimpleDateFormat(pattern);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				pattern = "yyyy-MM-dd";
				sd = new SimpleDateFormat(pattern);
				res = sd.parse(date);
				success = true;
			} catch (Exception e) {
			}
		}
		if(!success){
			try {
				sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				res = sd.parse(date);
			} catch (Exception e2) {
				System.out.println("Error parsing date: " + date + " [Type: " + rssType + "] --" + e2.toString());
			}
		}

		return res;
	}

}
