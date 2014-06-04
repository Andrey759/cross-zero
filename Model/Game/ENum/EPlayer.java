package Model.Game.ENum;

public enum EPlayer {

    FirstPlayer,
    SecondPlayer;

    private EField label = EField.Empty;

    public EField getLabel() {
        return label;
    }

    public void setLabel(EField label) {
        this.label = label;
    }

}
