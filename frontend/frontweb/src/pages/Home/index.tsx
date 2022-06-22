import axios from 'axios';
import moment from 'moment';
import React, { useState } from 'react';
import formatValue from '../../utils/formatValue';

interface Store {
    id: Number,
    name: String,
    balance: Number,
    transactions: Transactions[]
}

interface Transactions {
    date: Date,
    value: Number,
    description: String,
    signal: String
}

const Home: React.FC = () => {
    const [file, setFile] = useState<string | Blob>(new Blob());
    const [data, setData] = useState<Store[]>([]);
    const [expend, setExpend] = useState<Number[]>([]);

    const handleExpend = (id: Number) => {
        const alreadySelected = expend.findIndex(item => item === id);

        if (alreadySelected >= 0) {
            const filteredItems = expend.filter(item => item !== id);
            setExpend(filteredItems);
        } else {
            setExpend([...expend, id]);
        }
    }

    const saveFile = (e: any) => {
        setFile(e.target.files[0]);
    }

    const uploadFile = async () => {
        const formData = new FormData;
        formData.append('file', file);
        axios.post('http://localhost:8080/api/upload', formData, {
            headers: {
                'Access-Control-Allow-Origin': true,
                'Content-Type': 'multipart/form-data'
            }
        }).then((response: any) => {
            console.log(response.data)
            getStores();
        })
            .catch((error: any) => {
                console.log(error);
            });
    }

    const getStores = async () => {
        axios.get('http://localhost:8080/api/stores', {
        }).then((response: any) => {
            setData(response.data);
            console.log(response.data)
        })
            .catch((error: any) => {
                console.log(error);
            });
    }

    const listStores = data.map((stores: Store) =>
        <>
            <li className="border" style={{ marginBottom: '8px' }} onClick={() => handleExpend(stores.id)}>
                <div className="card-header" style={{ cursor: 'pointer' }}>
                    <>
                        {`${stores.id} - ${stores.name} Saldo: ${formatValue(stores.balance)}`}
                    </>
                </div>
                <ul className={expend.includes(stores.id) ? 'list-group list-group-flush' : 'd-none'}>
                    {/* {
                        stores.transactions.map((transaction: Transactions) =>
                            <>
                                <li className="list-group-item">
                                    <>
                                        {`   (${transaction.signal}) ${transaction.description} ${transaction.date} ${formatValue(transaction.value)}`}
                                    </>
                                </li>
                            </>
                        )
                    } */}

                    <div className="table-responsive sm">
                        <table className="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Descrição</th>
                                    <th>Data</th>
                                    <th>Valor</th>
                                </tr>
                            </thead>
                            <tbody>
                                {stores.transactions.map((transaction: Transactions) => ( 
                                    <tr>
                                        <td>{transaction.signal}</td>
                                        <td>{transaction.description}</td>
                                        <td>{moment(transaction.date).format('DD/MM/YYYY')}</td>
                                        <td>{formatValue(transaction.value)}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </ul>
            </li>
        </>
    );

    return (
        <div className="container-fluid">
            <div className="row mt-5 mb-5">
                <div className="col">
                    <div className="form-group">
                        <h1>Processa Arquivo CNAB</h1>
                        <input className="form-control-file" style={{ marginLeft: '5px' }} type="file" onChange={saveFile} accept=".txt" />
                        <button className="btn btn-primary mb-2" onClick={uploadFile}>Enviar</button>
                    </div>
                </div>
            </div>
            <div className="row">
                <ul className="list-inline">
                    {listStores}
                </ul>
            </div>
        </div>
    );
}

export default Home;