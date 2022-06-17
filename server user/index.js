require('dotenv').config()
const express = require('express')
const app = express()

const connection = require('./db')
const userRoutes = require("./routes/users")
const authRoutes = require("./routes/auth")

connection()

app.use(express.json())
const cors = require('cors');
const corsOptions ={
    origin:'http://localhost:3000', 
    credentials:true,            //access-control-allow-credentials:true
    optionSuccessStatus:200
}
app.use(cors(corsOptions));
app.use("/api/users", userRoutes)
app.use("/api/auth", authRoutes)
const port = process.env.PORT || 8081
app.listen(port, () => console.log(`Nasłuchiwanie na porcie ${port}`))