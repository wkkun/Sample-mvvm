package com.wkkun.base.net

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager


//net 证书相关

class TrustAllCertsManager :X509TrustManager{
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }

}