package com.huak.mdc.vo;

import java.io.Serializable;

public class MeterCollectA  implements Serializable{


    private static final long serialVersionUID = 9090351763739071516L;

        private String id;

        private String code;

        private String name;

        private String serialNo;

        private String energyTypeId;

        private Byte isreal;

        private Byte istotal;

        private String unitId;

        private String unitname;

        private Byte unitType;

        private Byte isauto;

        private String depict;

        private String tag;

        private Double coef;

        private String formula;

        private Byte isprestore;

        private Byte isdelete;

        private String comId;

        public MeterCollectA(String id, String code, String name, String serialNo, String energyTypeId, Byte isreal, Byte istotal, String unitId,String unitname, Byte unitType, Byte isauto, String depict, String tag, Double coef, String formula, Byte isprestore, Byte isdelete, String comId) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.serialNo = serialNo;
            this.energyTypeId = energyTypeId;
            this.isreal = isreal;
            this.istotal = istotal;
            this.unitId = unitId;
            this.unitname=unitname;
            this.unitType = unitType;
            this.isauto = isauto;
            this.depict = depict;
            this.tag = tag;
            this.coef = coef;
            this.formula = formula;
            this.isprestore = isprestore;
            this.isdelete = isdelete;
            this.comId = comId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id == null ? null : id.trim();
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code == null ? null : code.trim();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo == null ? null : serialNo.trim();
        }

        public String getEnergyTypeId() {
            return energyTypeId;
        }

        public void setEnergyTypeId(String energyTypeId) {
            this.energyTypeId = energyTypeId == null ? null : energyTypeId.trim();
        }

        public Byte getIsreal() {
            return isreal;
        }

        public void setIsreal(Byte isreal) {
            this.isreal = isreal;
        }

        public Byte getIstotal() {
            return istotal;
        }

        public void setIstotal(Byte istotal) {
            this.istotal = istotal;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId == null ? null : unitId.trim();
        }
        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }
        public Byte getUnitType() {
            return unitType;
        }

        public void setUnitType(Byte unitType) {
            this.unitType = unitType;
        }

        public Byte getIsauto() {
            return isauto;
        }

        public void setIsauto(Byte isauto) {
            this.isauto = isauto;
        }

        public String getDepict() {
            return depict;
        }

        public void setDepict(String depict) {
            this.depict = depict == null ? null : depict.trim();
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag == null ? null : tag.trim();
        }

        public Double getCoef() {
            return coef;
        }

        public void setCoef(Double coef) {
            this.coef = coef;
        }

        public String getFormula() {
            return formula;
        }

        public void setFormula(String formula) {
            this.formula = formula == null ? null : formula.trim();
        }

        public Byte getIsprestore() {
            return isprestore;
        }

        public void setIsprestore(Byte isprestore) {
            this.isprestore = isprestore;
        }

        public Byte getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(Byte isdelete) {
            this.isdelete = isdelete;
        }

        public String getComId() {
            return comId;
        }

        public void setComId(String comId) {
            this.comId = comId == null ? null : comId.trim();
        }
    }
