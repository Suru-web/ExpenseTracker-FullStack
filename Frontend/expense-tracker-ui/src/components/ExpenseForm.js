import { useEffect, useState } from "react";
import api from "../api/api";

export default function ExpenseForm({ onSaved }) {
    const [amount, setAmount] = useState("");
    const [desc, setDesc] = useState("");
    const [date, setDate] = useState("");
    const [categories, setCategories] = useState([]);
    const [categoryId, setCategoryId] = useState("");

    useEffect(() => {
        api.get("/categories").then((res) => {
            setCategories(res.data);
            if (res.data.length > 0) {
                setCategoryId(res.data[0].id); // default
            }
        });
    }, []);

    const submit = async (e) => {
        e.preventDefault();

        await api.post("/expenses", {
            amount,
            description: desc,
            expenseDate: date,
            categoryId,
        });

        setAmount("");
        setDesc("");
        setDate("");
        onSaved();
    };

    return (
        <form style={styles.form} onSubmit={submit}>
            <input
                placeholder="Amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />

            <input
                placeholder="Description"
                value={desc}
                onChange={(e) => setDesc(e.target.value)}
            />

            <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />

            <select
                value={categoryId}
                onChange={(e) => setCategoryId(e.target.value)}
            >
                {categories.map((c) => (
                    <option key={c.id} value={c.id}>
                        {c.name}
                    </option>
                ))}
            </select>

            <button>Add</button>
        </form>
    );
}

const styles = {
    form: {
        display: "grid",
        gridTemplateColumns: "1fr 2fr 1.5fr 1.5fr auto",
        gap: "10px",
        marginBottom: "20px",
    },
};