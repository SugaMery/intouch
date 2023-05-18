package sn.intouch.gu.api.ejb.services;

import java.util.List;

import javax.ejb.Remote;

import sn.intouch.gu.api.ejb.entities.Network;
import sn.intouch.gu.api.ejb.entities.SalePoint;

@Remote
public interface SalePointService {

	public java.util.List<SalePoint> listerSalePoint();

	public void CreerSalePoint(SalePoint sp);

	public SalePoint ModifierSalePoint(SalePoint sp);

	public SalePoint SupprimerSalePoint(SalePoint sp);

	public SalePoint getSalePoint(Long code);

	public List<SalePoint> findAll();

	public List<SalePoint> listerSalePointNetwork(String reseau);

	public List<SalePoint> findAll(String login);

	public SalePoint getSalePointByName(String nom);

	public SalePoint getSalePointByNom(String code);

	public SalePoint getSalePointByNumSIM1(Long code);

	public SalePoint getSalePointByNumSIM2(Long code);

	public SalePoint getSalePointByCode(String code);

	public List<SalePoint> listerSalePoints(Network networks);

	public SalePoint getSalePointByEtat(Boolean code);

	public List<SalePoint> listerSalePointTotal();

	public SalePoint getSalePointByCodeSupervisor(String login);

	public List<SalePoint> findSalePointByNetwork(Long idNetwork);

	public List<SalePoint> listerAllSalePoint();

	public List<SalePoint> findSalePointsBySupervisor(String login);
}
