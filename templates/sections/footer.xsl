<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="footer">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="right">
            <fo:page-number/>/<fo:page-number-citation-last ref-id="end"/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>