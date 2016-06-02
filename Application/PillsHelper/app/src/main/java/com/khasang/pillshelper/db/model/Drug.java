package com.khasang.pillshelper.db.model;

import com.khasang.pillshelper.db.PillsDBHelper;

public class Drug {
    private final int id;
    private String name;
    private String firm;
    private String pharmGroup;
    private String pharmAction;
    private String usage;
    private String dosage;
    private String sideEffect;
    private String contras;
    private String special;
    private String overdose;
    private String interaction;
    private String storage;
    private String composition;

    public int getId() {
        return id;
    }

    public String getName() {
        if(name == null){
            name = PillsDBHelper.getInstance().getDrugNameByID(id);
        }
        return name;
    }

    public String getFirm() {
        if(firm == null){
            firm = PillsDBHelper.getInstance().getDrugFirmByID(id);
        }
        return firm;
    }

    public String getPharmGroup() {
        if(pharmGroup == null){
            pharmGroup = PillsDBHelper.getInstance().getDrugPharmGroupByID(id);
        }
        return pharmGroup;
    }

    public String getPharmAction() {
        if(pharmAction == null){
            pharmAction = PillsDBHelper.getInstance().getDrugPharmActionByID(id);
        }
        return pharmAction;
    }

    public String getUsage() {
        if(usage == null){
            usage = PillsDBHelper.getInstance().getDrugUsageByID(id);
        }
        return usage;
    }

    public String getDosage() {
        if(dosage == null){
            dosage = PillsDBHelper.getInstance().getDrugDosageByID(id);
        }
        return dosage;
    }

    public String getSideEffect() {
        if(sideEffect == null){
            sideEffect = PillsDBHelper.getInstance().getDrugSideEffectByID(id);
        }
        return sideEffect;
    }

    public String getContras() {
        if(contras == null){
            contras = PillsDBHelper.getInstance().getDrugContrasByID(id);
        }
        return contras;
    }

    public String getSpecial() {
        if(special == null){
            special = PillsDBHelper.getInstance().getDrugSpecialByID(id);
        }
        return special;
    }

    public String getOverdose() {
        if(overdose == null){
            overdose = PillsDBHelper.getInstance().getDrugOverdoseByID(id);
        }
        return overdose;
    }

    public String getInteraction() {
        if(interaction == null){
            interaction = PillsDBHelper.getInstance().getDrugInteractionByID(id);
        }
        return interaction;
    }

    public String getStorage() {
        if(storage == null){
            storage = PillsDBHelper.getInstance().getDrugStorageByID(id);
        }
        return storage;
    }

    public String getComposition() {
        if(composition == null){
            composition = PillsDBHelper.getInstance().getDrugCompositionByID(id);
        }
        return composition;
    }

    public Drug(int id){
        this.id = id;
    }

    public Drug(int id, String name){
        this.id = id;
        this.name = name;
    }
}
