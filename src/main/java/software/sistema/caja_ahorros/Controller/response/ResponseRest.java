package software.sistema.caja_ahorros.Controller.response;

import java.util.List;

public class ResponseRest<T> {

    private List<T> data;
    private List<InfoRest> infoRestList;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<InfoRest> getInfoList() {
        return infoRestList;
    }

    public void setInfoList(List<InfoRest> infoList) {
        this.infoRestList = infoList;
    }

}
