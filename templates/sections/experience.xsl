<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="job-experience.xsl"/>

    <xsl:template name="experience">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block font-size="10pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle">
                            <fo:block text-align="left">

                                <fo:block margin-left="0px">
                                    <fo:external-graphic src="classpath:static/icon.png"
                                                         content-height="32px"
                                                         content-width="32px"/>
                                </fo:block>
                                <fo:block font-size="16pt" padding-bottom="10px" text-align="center">
                                    <xsl:value-of select="$root/introductionTitle"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <xsl:for-each select="$root/experienceList">
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block wrap-option="wrap" text-align="justify">
                                    <xsl:call-template name="job-experience">
                                        <xsl:with-param name="item" select="item"/>
                                        <xsl:with-param name="title" select="''"/>
                                    </xsl:call-template>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>