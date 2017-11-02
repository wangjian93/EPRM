package com.ivo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.equipment.IDepOfClassDao;
import com.ivo.dao.equipment.IEquipmentGroupDao;
import com.ivo.dao.equipment.ISpecDao;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.model.equipment.Spec;
import com.ivo.service.ISpecService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年10月12日 - 下午2:51:02
 *@description:
 */
@Service("specService")
public class SpecServiceImpl implements ISpecService{
	@Resource  
	   private ISpecDao specDao;
	@Resource
	private IEquipmentGroupDao equipmentGroupDao;

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
	
	@Override
	public void createCurrentMonthSpec(){
		List<EquipmentGroup> equipmentGroupList = equipmentGroupDao.getAll();
		for(EquipmentGroup equipmentGroup : equipmentGroupList){
			Spec spec = new Spec();
			spec.setYear(CurrentUtil.CurrentYear());
			spec.setMonth(CurrentUtil.CurrentMonth());
			spec.setSpec(0.90f);
			spec.setDepClassID(equipmentGroup.getClass_fk());
			spec.setEquipmentGroup(equipmentGroup.getEquipmentGroupID());
			this.saveSpec(spec);
		}
	}
	
	@Override
	public void updateCurrentMonthSpec(){
		int year = CurrentUtil.CurrentPerYear();
		int month = CurrentUtil.CurrentMonth();
		List<Spec> specList = this.getSpecByMonth(year, month);
		for(Spec spec : specList){
			Spec newSpec = new Spec();
			newSpec.setYear(CurrentUtil.CurrentYear());
			newSpec.setMonth(CurrentUtil.CurrentMonth());
			newSpec.setDepClassID(spec.getDepClassID());
			newSpec.setEquipmentGroup(spec.getEquipmentGroup());
			newSpec.setSpec(spec.getSpec());
			System.out.println(newSpec.getYear() + " " + newSpec.getMonth() + "   " + newSpec.getEquipmentGroupName() + "    " + newSpec.getSpec());
//			this.saveSpec(newSpec);
		}
	}

}
