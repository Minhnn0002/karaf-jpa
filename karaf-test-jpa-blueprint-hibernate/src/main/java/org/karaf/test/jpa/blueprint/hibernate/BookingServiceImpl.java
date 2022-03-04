package org.karaf.test.jpa.blueprint.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.karaf.test.jpa.api.BookingEntity;
import org.karaf.test.jpa.api.BookingService;

public class BookingServiceImpl implements BookingService {

	@PersistenceContext(unitName = "booking-hibernate")
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional(Transactional.TxType.SUPPORTS)
	@Override
	public List<BookingEntity> list() {
		TypedQuery<BookingEntity> query = entityManager.createQuery("SELECT b FROM Booking b", BookingEntity.class);
		return query.getResultList();
	}

	@Override
	public BookingEntity get(Long id) {
		TypedQuery<BookingEntity> query = entityManager.createQuery("SELECT b FROM Booking b WHERE b.id=:id",
				BookingEntity.class);
		query.setParameter("id", id);
		BookingEntity bookingEntity = null;
		try {
			bookingEntity = query.getSingleResult();
		} catch (NoResultException e) {
			// nothing to do
		}
		return bookingEntity;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	@Override
	public void add(BookingEntity booking) {
		entityManager.persist(booking);
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	@Override
	public void add(String flight, String customer) {
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setCustomer(customer);
		bookingEntity.setFlight(flight);
		entityManager.persist(bookingEntity);
	}

	@Override
	public void remove(Long id) {
		BookingEntity bookingEntity = get(id);
		entityManager.remove(bookingEntity);
	}

}
