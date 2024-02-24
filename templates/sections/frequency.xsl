<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="frequency">
        <xsl:param name="title" select="'default-value'"/>
        <xsl:param name="topX" select="'default-value'"/>

        <fo:table margin-top="10px" border="1px solid gray" font-size="7pt" space-before="0.5em" space-after="0.5em">

            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>

            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell number-columns-spanned="7" padding="2px">
                        <fo:block text-align="center">
                            <xsl:value-of select="$title"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <xsl:for-each select="$topX/item">
                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="1" padding="2px">
                            <fo:block>
                                <xsl:value-of select="key"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="6">
                            <fo:block margin-left="10px" text-align="left">
                                <xsl:value-of select="value"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>