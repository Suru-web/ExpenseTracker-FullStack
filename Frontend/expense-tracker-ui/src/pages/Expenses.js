import { useEffect, useState } from "react";
import api from "../api/api";
import ExpenseForm from "../components/ExpenseForm";
import { useAuth } from "../auth/AuthContext";

export default function Expenses() {
    const [expenses, setExpenses] = useState([]);
    const { logout } = useAuth();

    const load = async () => {
        const res = await api.get("/expenses");
        setExpenses(res.data);
    };

    const remove = async (id) => {
        await api.delete(`/expenses/${id}`);
        load();
    };

    useEffect(() => {
        load();
    }, []);

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <h2>My Expenses</h2>
                <button className="secondary" onClick={logout}>Logout</button>
            </div>

            <ExpenseForm onSaved={load} />

            <div style={styles.list}>
                {expenses.map((e) => (
                    <div key={e.id} style={styles.card}>
                        <div>
                            <strong>{e.description}</strong>
                            <div style={{ color: "#666", fontSize: "14px" }}>
                                {e.category}
                            </div>
                        </div>

                        <div style={styles.right}>
                            <span style={{ fontWeight: "600" }}>₹{e.amount}</span>
                            <button className="danger" onClick={() => remove(e.id)}>
                                Delete
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

const styles = {
    container: {
        maxWidth: "700px",
        margin: "40px auto",
        padding: "0 20px",
    },
    header: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        marginBottom: "20px",
    },
    list: {
        display: "flex",
        flexDirection: "column",
        gap: "12px",
    },
    card: {
        background: "white",
        padding: "16px",
        borderRadius: "8px",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        boxShadow: "0 4px 10px rgba(0,0,0,0.05)",
    },
    right: {
        display: "flex",
        gap: "10px",
        alignItems: "center",
    },
};