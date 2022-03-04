package org.karaf.test.jpa.api;

import java.util.List;

public interface BookingService {
	List<BookingEntity> list();

	BookingEntity get(Long id);

	void add(BookingEntity booking);

	void add(String flight, String customer);

	void remove(Long id);
}
