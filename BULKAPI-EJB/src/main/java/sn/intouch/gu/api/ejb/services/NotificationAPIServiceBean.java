package sn.intouch.gu.api.ejb.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.intouch.gu.api.ejb.entities.NotificationAPI;

@Stateless
public class NotificationAPIServiceBean implements NotificationAPIService {
	@PersistenceContext(unitName = "guAPIDistPU")
	EntityManager em;

	@Override
	public NotificationAPI addNotification(NotificationAPI notification) {
		return em.merge(notification);
	}

}
