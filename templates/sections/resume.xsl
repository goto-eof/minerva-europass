<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="profile.xsl"/>
    <xsl:template name="resume">
        <xsl:param name="root" select="'default-value'"/>
        <fo:table table-layout="fixed" width="100%" font-size="7pt" space-before="0.5em" space-after="0.5em">

            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>

            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell height="26cm" background-color="rgb(240,240,240)" number-columns-spanned="2"
                                   padding="2px">
                        <fo:block>
                            <xsl:call-template name="profile">
                                <xsl:with-param name="root" select="root"/>
                            </xsl:call-template>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell number-columns-spanned="4" padding="2px">
                        <fo:block>
                            mondo
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>