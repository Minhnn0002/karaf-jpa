package org.karaf.test.jpa.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.karaf.test.jpa.api.BookingEntity;
import org.karaf.test.jpa.blueprint.hibernate.BookingServiceImpl;
import org.osgi.service.component.annotations.Component;

@Path("/booking")
@Component(service=BookingServiceRest.class,property = {"osgi.jaxrs.resource=true"})
public class BookingServiceRest {
	BookingServiceImpl bookingServiceImpl ;
	private final Map<Long, BookingEntity> bookings = new HashMap<>();
	@Path("/")
	@Produces("application/json")
	@GET
	public Collection<BookingEntity> list(){
	return bookingServiceImpl.list();
//		return  bookings.values();
	}
	
	@Path("/{id}")
	@Produces("application/json")
	@GET
	public BookingEntity get(@PathParam("id") Long id) {
		return bookingServiceImpl.get(id);
	}
	
	@Path("/")
	@Consumes("application/json")
	@POST
	public void add(BookingEntity bookingEntity) {
		bookingServiceImpl.add(bookingEntity);
//		bookings.put(bookingEntity.getId(), bookingEntity);
	}
	
	@Path("/{id}")
	@DELETE
	public void remove(@PathParam("id") Long id) {
		bookingServiceImpl.remove(id);
	}
}
