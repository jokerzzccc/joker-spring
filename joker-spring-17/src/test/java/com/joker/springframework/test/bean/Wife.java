package com.joker.springframework.test.bean;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/6
 */
public class Wife {

    private Husband husband;
    private IMother mother; // 婆婆

    public String queryHusband() {
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
