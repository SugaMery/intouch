package sn.intouch.gu.api.ejb.services;

import javax.ejb.Remote;
import sn.intouch.gu.api.ejb.entities.NotificationAPI;

@Remote
public interface NotificationAPIService {
	public NotificationAPI addNotification(NotificationAPI notification);
}
