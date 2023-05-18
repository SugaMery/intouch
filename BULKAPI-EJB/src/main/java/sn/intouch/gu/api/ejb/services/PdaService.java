package sn.intouch.gu.api.ejb.services;

import java.util.List;

import sn.intouch.gu.api.ejb.entities.PDA;

public interface PdaService {

	PDA save(PDA pda) throws Exception;

	List<PDA> lister(int debut, int nbParPage);

	PDA supprimer(PDA gerant);

	Long compter();

	PDA findPDAById(int idPDA);

	List<PDA> findAllPDA();

	// Station findStationOfPDA(int idPDA);

	PDA findPDAByCode(String codePDA);

	List<PDA> filtrerByEtatAndStation(String requete);

	PDA findPDAByIdSIM(String idSim);

	List<PDA> findPDAByStation(String staCode);

}
