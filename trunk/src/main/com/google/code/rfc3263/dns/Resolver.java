package com.google.code.rfc3263.dns;

import java.util.SortedSet;

public interface Resolver {
	SortedSet<PointerRecord> lookupPointerRecords(String domain, boolean isSecure);
}
