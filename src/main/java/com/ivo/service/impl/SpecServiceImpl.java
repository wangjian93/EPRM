package com.ivo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.equipment.IDepOfClassDao;
import com.ivo.dao.equipment.ISpecDao;
import com.ivo.model.equipment.Spec;
import com.ivo.service.ISpecService;

/**
 *@author wangjian
 *@time 2017年10月12日 - 下午2:51:02
 *@description:
 */
@Service("specService")
public class SpecServiceImpl implements ISpecService{
	@Resource  
	   private ISpecDao specDao;

	public ISpecDao getSpecDao() {
		return specDao;
	}

	public void setSpecDao(ISpecDao specDao) {
		this.specDao = specDao;
	}

	@Override
	public List<Spec> getAllSpec() {
		return specDao.getAllSpec();
	}

	@Override
	public List<Spec> getSpecByMonth(int year, int month) {
		return specDao.getSpecByMonth(year, month);
	}

	@Override
	public Spec getSpec(int specID) {
		return specDao.getSpec(specID);
	}

	@Override
	public void saveSpec(Spec spec) {
		specDao.saveSpec(spec);
	}

	@Override
	public void updateSpec(Spec spec) {
		specDao.updateSpec(spec);
	}

	@Override
	public Spec getSpecByEquipmentGroup(int year, int month, int equipmentGroup) {
		return specDao.getSpecByEquipmentGroup(year, month, equipmentGroup);
	}

}
