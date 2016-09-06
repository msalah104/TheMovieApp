package msalah.mal.com.themovieapp.controllers.connection;


public abstract class ConnectionManager implements RequestDataSource{

    Connection connection;

    protected Object tag;

    protected OnDataReceivedListener onDataReceivedListener;

    public ConnectionManager(OnDataReceivedListener onDataReceivedListener, Object tag) {
        this.onDataReceivedListener = onDataReceivedListener;
        this.tag = tag;
    }

    public void fireRequest(){

        connection = new Connection();
        connection.execute(this);

    }

    @Override
    public Object getRequestTag() {
        return tag;
    }

    @Override
    public OnDataReceivedListener getReceiverListener() {
        return onDataReceivedListener;
    }
}
