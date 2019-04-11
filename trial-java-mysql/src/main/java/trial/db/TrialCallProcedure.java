package trial.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrialCallProcedure {

    public static void main(String[] args) {
        try {
            TrialCallProcedure me = new TrialCallProcedure();
            me.execute(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void execute(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try {
            List<Callable<Boolean>> taskList = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                taskList.add(() -> {
                    return callProcedureAndUpdatePrefix1();
                });
                taskList.add(() -> {
                    return callProcedureAndUpdatePrefix2();
                });
            }
            pool.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private boolean callProcedureAndUpdatePrefix1() {
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);
            callProcedure1(conn);
            updateSeq1(conn);
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean callProcedureAndUpdatePrefix2() {
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);
            callProcedure2(conn);
            updateSeq2(conn);
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void callProcedure1(Connection con) throws SQLException {
        try (CallableStatement cs = con.prepareCall("call nextval_seq1(?)")) {
            cs.executeQuery();
            System.out.println(cs.getString(1));
        }
    }

    private void callProcedure2(Connection con) throws SQLException {
        try (CallableStatement cs = con.prepareCall("call nextval_seq2(?)")) {
            cs.executeQuery();
            System.out.println(cs.getString(1));
        }
    }

    private void updateSeq1(Connection con) throws SQLException {
        boolean b = (new Random()).nextInt(99) % 2 == 0 ? true : false;
        String sql = "update seq1 set prefix = " + (b ? "'XA'" : "'XB'");
        try (Statement st = con.createStatement()) {
            st.execute(sql);
        }
    }

    private void updateSeq2(Connection con) throws SQLException {
        boolean b = (new Random()).nextInt(99) % 2 == 0 ? true : false;
        String sql = "update seq2 set prefix = " + (b ? "'YA'" : "'YB'");
        try (Statement st = con.createStatement()) {
            st.execute(sql);
        }
    }
}
